package sg.edu.np.mad.madpractical5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class UserViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView_user,  imageView_large_user;
    private TextView textView_name;
    private TextView textView_description;
    private User user;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_user = itemView.findViewById(R.id.imageView_user);
        textView_name = itemView.findViewById(R.id.textView_name);
        textView_description = itemView.findViewById(R.id.textView_description);
        imageView_large_user = itemView.findViewById(R.id.imageView_large_user);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(textView_name.getText().toString())
                        .setTitle("User Info")
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Context context = v.getContext();
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.putExtra("user", user);
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Dismiss the dialog
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void bind(User user) {
        this.user = user;
        // Bind data to views here
        textView_name.setText(user.getName());
        textView_description.setText(user.getDescription());
        // Assuming you also have an image for each user
        imageView_user.setImageResource(R.drawable.ic_launcher_foreground);
        if (imageView_large_user != null) {
            imageView_large_user.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }
}