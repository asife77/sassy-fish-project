package eus.ehu.controllers;

import java.time.LocalDate;

import eus.ehu.usermodel.Comment;
import eus.ehu.usermodel.Post;
import eus.ehu.usermodel.User;
import eus.ehu.businesslogic.BlInterface;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class CommentOnPostController {

    @FXML
    private TextField wordCount;

    @FXML
    private TextArea commentArea;

    @FXML
    private Button saveButton;

    // variables to store the context of the comment
    private Post currentPost;
    private User currentUser;
    private BlInterface businessLogic; 

    @FXML
    void initialize() {
        int maxCharacters = 150;
        
        // create a textformatter to prevent exceeding the character limit
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= maxCharacters) {
                return change;
            }
            // reject the change if it exceeds the limit
            return null; 
        });
        
        commentArea.setTextFormatter(textFormatter);

        // show the character count dynamically binding the text property
        wordCount.textProperty().bind(
            Bindings.createStringBinding(
                () -> commentArea.getText().length() + "/" + maxCharacters,
                commentArea.textProperty()
            )
        );
    }

    // method to receive data from the window that opens this controller
    public void initData(Post post, User user, BlInterface bl) {
        this.currentPost = post;
        this.currentUser = user;
        this.businessLogic = bl;
    }

    @FXML
    void handleSave() {
        String commentText = commentArea.getText();
        
        if (commentText.trim().isEmpty()) {
            System.out.println("comment is empty!");
            return;
        }
        if(currentPost == null || currentUser == null) {
            System.out.println("post or user context is missing!");
            return;
        }
        System.out.println("comment saved: " + commentText);
        
        // 1. create the comment object
        Comment newComment = new Comment(currentUser.getUsername(), commentText, LocalDate.now(), currentPost);
        
        // 2. add the comment to the post internally
        //currentPost.addComment(newComment); 
        // not needed anymore since the business logic will handle this

        // 3. TODO: send it to the database via business logic
        businessLogic.addCommentToPost(currentPost, newComment);
        
        // clear the comment area after saving
        commentArea.clear();
    }
}