package eus.ehu.controllers;

import eus.ehu.data_access.DbAccessManager;
import eus.ehu.usermodel.Post;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FeedController {

    @FXML
    private ScrollPane feedScroll;
    @FXML
    private Button newPostButton;
    @FXML
    private Button profileButton;
    @FXML
    private VBox feedContainer;
    @FXML
    private HBox PostMockup;

    @FXML
    void initialize() {
        try {
            DbAccessManager dbManager = new DbAccessManager();
            showPosts(dbManager.getAllPosts());
            dbManager.close();
        } catch (Exception e) {
            System.err.println("Could not load feed from DB: " + e.getMessage());
        }
    }

    public void showPosts(List<Post> posts) {
        feedContainer.getChildren().clear();
        for (Post post : posts) {
            feedContainer.getChildren().add(createPostCard(post));
        }
    }
    @FXML
    void newPostButtonClicked() {
        System.out.println("New Post button clicked!");

    }
    @FXML
    void openProfile() {
        System.out.println("Profile button clicked!");
    }
    public HBox createPostCard(Post post) {
        HBox postCard = new HBox();
        Image postImage = null;
        String imagePath = post.getImagePath();
        if (imagePath != null && !imagePath.isBlank()) {
            try {
                postImage = new Image(imagePath);
            } catch (Exception ignored) {
                postImage = null;
            }
        }

        if (postImage == null) {
            var defaultImageStream = getClass().getResourceAsStream("/default.png");
            if (defaultImageStream != null) {
                postImage = new Image(defaultImageStream);
            }
        }

        if (postImage != null) {
            ImageView imageView = new ImageView(postImage);
            imageView.setFitWidth(200);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            postCard.getChildren().add(imageView);
        }

        VBox postContent = new VBox();
        Label titleLabel = new Label(post.getTitle());
        titleLabel.setStyle("-fx-text-fill: #111111;");

        Label authorLabel = new Label("by " + post.getAuthor());
        authorLabel.setStyle("-fx-text-fill: #222222;");

        Label descriptionLabel = new Label(post.getDescription());
        descriptionLabel.setStyle("-fx-text-fill: #1f2937;");

        postContent.getChildren().addAll(titleLabel, authorLabel, descriptionLabel);
        postCard.getChildren().add(postContent);
        // Here you would create and style the components of the post card
        // For example, you could add Labels for the title, author, and content
        return postCard;
    }
    
}