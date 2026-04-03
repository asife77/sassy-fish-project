package eus.ehu.businesslogic;

import java.util.List;

import eus.ehu.data_access.DbAccessManager;
import eus.ehu.usermodel.Comment;
import eus.ehu.usermodel.Post;

public class BusinessLogic implements BlInterface {
    private DbAccessManager dbManager = new DbAccessManager();
    private List<Post> mockDatabase = new java.util.ArrayList<>(); 
    
    // this is a mock database, in a real application this would be replaced by actual database calls
    @Override
    public void addCommentToPost(Post post, Comment comment) {
        
        // 1. link the comment to the post in memory
        // post.addComment(comment);
        // not needed, will be handled by the database relationship when we save the comment

        // 2. TODO: save the comment to the database
        dbManager.storeComment(post, comment);
        System.out.println("mock db: saved comment '" + comment.getText() + "' from user '" + comment.getAuthor() + "'");
    }

    @Override
    public void savePost(Post post) {
        // add the post to our fake database
        dbManager.storePost(post);
        System.out.println("mock db: successfully saved post titled '" + post.getTitle() + "'");
    }

    @Override
    public List<Post> getAllPosts() {
        // return the list of posts so the feed controller can display them
        return mockDatabase;
    }
}