package com.sakura.book_recommodation.VO;

import com.sakura.book_recommodation.domain.User2favorite;
import com.sakura.book_recommodation.domain.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersVO extends Users {
    private List<User2favorite> user2favorites = new ArrayList<>();

    public List<User2favorite> getUser2favorites() {
        return user2favorites;
    }

    public void setUser2favorites(List<User2favorite> user2favorites) {
        this.user2favorites = user2favorites;
    }
}
