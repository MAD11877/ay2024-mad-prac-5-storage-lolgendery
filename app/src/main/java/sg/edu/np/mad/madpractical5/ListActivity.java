package sg.edu.np.mad.madpractical5;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;



public class ListActivity extends AppCompatActivity {
    private ArrayList<User> list;
    private ImageView imageView;
    private UserAdapter userAdapter;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHandler = new DatabaseHandler(this, null, null, 1);

        // Check if database is empty
        list = dbHandler.getUsers();
        if (list.isEmpty()) {
            // Database is empty, generate and insert random users
            generateRandomUsers();
        }

        // Apply recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(list, this);
        recyclerView.setAdapter(userAdapter);
    }

    // Method to generate random users
    private void generateRandomUsers() {
        list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String name = generateRandomName();
            String description = generateRandomDescription();
            boolean followed = generateRandomFollowedStatus();

            // Create user object and add to the list
            User user = new User(name, description, i, followed);
            list.add(user);

            // Insert user into the database
            dbHandler.addUser(user);
        }
    }

    // Generate a random name with appended random integers
    private String generateRandomName() {
        Random random = new Random();
        int randomNumber = random.nextInt(9999999); // Generates random integer between 0 and 9999999
        return "Name" + randomNumber;
    }

    // Generate a random description with appended random integers
    private String generateRandomDescription() {
        Random random = new Random();
        int randomNumber = random.nextInt(9999999); // Generates random integer between 0 and 9999999
        return "Description" + randomNumber;
    }

    // Generate a random followed status
    private boolean generateRandomFollowedStatus() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
