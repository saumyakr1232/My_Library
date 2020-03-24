package com.example.mylibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.net.URL;
import java.util.ArrayList;

public class BooksRecViewAdapter  extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder>{
    private static final String TAG = "BooksRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private String type = "";
    private Util utils;



    public BooksRecViewAdapter(Context context) {
        this.context = context;
        utils = new Util();
    }
    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listite_books_rec_view, parent,false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.bookName.setText(books.get(position).getName());
        holder.bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("bookId", books.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.bookCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Book book = books.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Deleting " + book.getName())
                        .setMessage("Are you sure you want to delete "+ book.getName())
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                switch (type){
                    case "want to read":
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(utils.removeWantToReadBooks(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context, book.getName() + "has successfully deleted", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).create().show();

                        break;
                    case "already read":

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(utils.removeAlreadyReadBooks(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context, book.getName() + "has successfully deleted", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).create().show();


                        break;
                    case "currently reading":
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(utils.removeCurrentlyReadingBooks(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context, book.getName() + "has successfully deleted", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).create().show();


                        break;
                    default:
                        break;
                }
                return true;

            }
        });


        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.bookImage);

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */

    @Override
    public int getItemCount() {
        return books.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView bookImage;
        private TextView bookName;
        private CardView bookCard;


        public ViewHolder(View itemView){
            super(itemView);

            bookImage = (ImageView) itemView.findViewById(R.id.bookImage);
            bookName = (TextView) itemView.findViewById(R.id.bookName);
            bookCard = (CardView) itemView.findViewById(R.id.bookCard);


        }

    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public void setType(String type) {
        this.type = type;
    }
}
