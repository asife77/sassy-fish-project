package eus.ehu.businesslogic;

import eus.ehu.usermodel.Comment;
import eus.ehu.usermodel.Post;

public interface BlInterface {
    
    // adds a comment to a specific post
    void addCommentToPost(Post post, Comment comment);
    
}