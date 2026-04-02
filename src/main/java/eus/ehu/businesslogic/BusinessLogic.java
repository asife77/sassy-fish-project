package eus.ehu.businesslogic;

import eus.ehu.usermodel.Comment;
import eus.ehu.usermodel.Post;

public class BusinessLogic implements BlInterface {

    // normally, you would have your database access manager here
    // private DbAccessManager db = new DbAccessManager();

    @Override
    public void addCommentToPost(Post post, Comment comment) {
        
        // 1. link the comment to the post in memory
        post.addComment(comment);

        // 2. TODO: save the comment to the database
        System.out.println("mock db: saved comment '" + comment.getText() + "' from user '" + comment.getAuthor() + "'");
    }
}