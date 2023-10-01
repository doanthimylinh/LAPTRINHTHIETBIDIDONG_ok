package vn.com.linh.ontaptk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class AuthorArrayAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Author> authors;

    public AuthorArrayAdapter(Context context, int layout, ArrayList<Author> authors) {
        this.context = context;
        this.layout = layout;
        this.authors = authors;
    }

    @Override
    public int getCount() {
        return authors.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView txtAuthorId = view.findViewById(R.id.txt_author_id);
        TextView txtAuthorName = view.findViewById(R.id.txt_author_name);
        TextView txtAuthorAddress = view.findViewById(R.id.txt_author_address);
        TextView txtAuthorEmail = view.findViewById(R.id.txt_author_email);

        Author author = authors.get(i);

        txtAuthorId.setText("Id: " + author.getId());
        txtAuthorName.setText("Name: " + author.getName());
        txtAuthorAddress.setText("Address: " + author.getAddress());
        txtAuthorEmail.setText("Email: " + author.getEmail());

        return view;
    }
}
