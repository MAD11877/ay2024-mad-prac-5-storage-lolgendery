package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button buttonFollow;
    private TextView tvName, tvDescription;
    private boolean followed;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);
        // Initialize views
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        buttonFollow = findViewById(R.id.btnFollow);

        // Get user information from intent
        Intent intent = getIntent();
        if (intent != null) {
            currentUser = (User) intent.getSerializableExtra("user");
            if (currentUser != null) {
                // Set user information
                tvName.setText(currentUser.getName());
                tvDescription.setText(currentUser.getDescription());
                // Set initial follow state
                followed = currentUser.getFollowed();
                updateButtonMessageText();
            }
        }
        //Reading the random number
        Intent receivingEnd = getIntent();
        String name = receivingEnd.getStringExtra("name");
        String description = receivingEnd.getStringExtra("description");
        String followed = receivingEnd.getStringExtra("followed");
        String id = receivingEnd.getStringExtra("id");

        User user = dbHandler.getUser(name);
        // Setup follow button click listener
        setupFollowButton();

    }

    private void setupFollowButton() {
        buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle follow state
                currentUser.setFollowed(!currentUser.getFollowed());
                updateButtonMessageText();
                String toastMessage = currentUser.getFollowed() ? getString(R.string.button_follow_text) : getString(R.string.button_unfollow_text);
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateButtonMessageText() {
        buttonFollow.setText(currentUser.getFollowed() ? R.string.button_unfollow_text : R.string.button_follow_text);
    }
}