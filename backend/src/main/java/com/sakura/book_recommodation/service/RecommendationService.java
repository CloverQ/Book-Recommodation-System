package com.sakura.book_recommodation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sakura.book_recommodation.VO.UsersVO;
import com.sakura.book_recommodation.domain.Book;
import com.sakura.book_recommodation.domain.BookExample;
import com.sakura.book_recommodation.domain.User2favorite;
import com.sakura.book_recommodation.domain.User2favoriteExample;
import com.sakura.book_recommodation.mapper.BookMapper;
import com.sakura.book_recommodation.mapper.User2favoriteMapper;
import com.sakura.book_recommodation.mapper.UsersMapper;
import com.sakura.book_recommodation.req.RecommendationQueryReq;
import com.sakura.book_recommodation.resp.PageResp;
import com.sakura.book_recommodation.resp.RecommendationQueryResp;
import com.sakura.book_recommodation.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    @Resource
    private User2favoriteMapper user2favoriteMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private UsersMapper usersMapper;

    public PageResp<RecommendationQueryResp> list(RecommendationQueryReq req) {
        List<Book> recommendedBooks = new ArrayList<>();
        // 获取用户收藏的图书id列表
        User2favoriteExample user2favoriteExample = new User2favoriteExample();
        User2favoriteExample.Criteria criteria = user2favoriteExample.createCriteria();
        criteria.andUserIdEqualTo(req.getUserId());
        UsersVO usersVO = usersMapper.selectByUsersId(req.getUserId());
        List<User2favorite> user2favoriteList = usersVO.getUser2favorites();
        List<Integer> bookIds = user2favoriteList.stream()
                .map(obj -> obj.getUserId())
                .collect(Collectors.toList());
        // 构建用户-图书评分矩阵
        Map<Integer, Map<Integer, Double>> ratingMatrix = new HashMap<>();
        for (Integer id : bookIds) {
            Map<Integer, Double> ratings = new HashMap<>();//

            User2favoriteExample user2favoriteExample1 = new User2favoriteExample();
            User2favoriteExample.Criteria criteria1 = user2favoriteExample1.createCriteria();
            List<User2favorite> user2favorites = user2favoriteMapper.selectByExample(user2favoriteExample1);
            List<User2favorite> collects = user2favorites.stream()
                    .filter(c -> c.getBookId().equals(id))
                    .collect(Collectors.toList());
            for (User2favorite collect : collects) {
                ratings.put(collect.getUserId(), 1.0);
            }
            ratingMatrix.put(id, ratings);
        }
        // 计算用户之间的相似度
        Map<Integer, Double> userSimilarity = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Double>> entry1 : ratingMatrix.entrySet()) {
            Integer bookId1 = entry1.getKey();
            Map<Integer, Double> ratings1 = entry1.getValue();
            for (Map.Entry<Integer, Map<Integer, Double>> entry2 : ratingMatrix.entrySet()) {
                Integer bookId2 = entry2.getKey();
                Map<Integer, Double> ratings2 = entry2.getValue();
                if (bookId1.equals(bookId2)) {
                    continue;
                }
                double similarity = cosineSimilarity(ratings1, ratings2);
                if (userSimilarity.containsKey(bookId1)) {
                    userSimilarity.put(bookId1, userSimilarity.get(bookId1) + similarity);
                } else {
                    userSimilarity.put(bookId1, similarity);
                }
            }
        }
        // 基于相似度进行推荐
        List<Map.Entry<Integer, Double>> sortedList = userSimilarity.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        List<Integer> recommendBookIds = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : sortedList) {
            Integer bookId = entry.getKey();
            if (!bookIds.contains(bookId)) {
                recommendBookIds.add(bookId);
            }
        }
        //如果推荐数量<18则随机推荐剩下的图书
        Random random = new Random();
        while (recommendBookIds.size() < 18) {
            int num = random.nextInt(48) + 1;
            if (!recommendBookIds.contains(num)) {
                recommendBookIds.add(num);
            }
        }

        for (Integer bookId : recommendBookIds) {
//            recommendedBooks.add(bookMapper.findById(bookId));
            BookExample bookExample = new BookExample();
            BookExample.Criteria criteria2 = bookExample.createCriteria();
            criteria2.andBookIdEqualTo(bookId);
            List<Book> bookList = bookMapper.selectByExample(bookExample);
            recommendedBooks.add(bookList.get(0));
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        PageInfo<Book> pageInfo = new PageInfo<>(recommendedBooks);
        LOG.info("Total lines: {}", pageInfo.getTotal());
        LOG.info("Total pages: {}", pageInfo.getPages());
        List<RecommendationQueryResp> list = CopyUtil.copyList(recommendedBooks, RecommendationQueryResp.class);
        PageResp<RecommendationQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
    // 计算余弦相似度
    private double cosineSimilarity(Map<Integer, Double> ratings1, Map<Integer, Double> ratings2) {
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        for (Map.Entry<Integer, Double> entry1 : ratings1.entrySet()) {
            Integer userId1 = entry1.getKey();
            Double rating1 = entry1.getValue();
            if (ratings2.containsKey(userId1)) {
                Double rating2 = ratings2.get(userId1);
                dotProduct += rating1 * rating2;
            }
            magnitude1 += rating1 * rating1;
        }
        for (Map.Entry<Integer, Double> entry2 : ratings2.entrySet()) {
            Double rating2 = entry2.getValue();
            magnitude2 += rating2 * rating2;
        }
        if (magnitude1 == 0.0 || magnitude2 == 0.0) {
            return 0.0;
        } else {
            return dotProduct / (Math.sqrt(magnitude1) * Math.sqrt(magnitude2));
        }
    }
}
