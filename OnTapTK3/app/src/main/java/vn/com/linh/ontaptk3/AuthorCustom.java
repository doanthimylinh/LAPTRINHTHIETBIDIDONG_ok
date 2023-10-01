package vn.com.linh.ontaptk3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AuthorCustom extends BaseAdapter {
    private Context context;
    private int layout;
    ArrayList<Author> authors;

    public AuthorCustom(Context context, int layout, ArrayList<Author> authors) {
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
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);

        TextView tv_id=view.findViewById(R.id.tv_idAuthor);
        TextView tv_name=view.findViewById(R.id.tv_nameAuthor);
        TextView tv_address=view.findViewById(R.id.tv_addressAuthor);
        TextView tv_email=view.findViewById(R.id.tv_emailAuthor);

        Author author=authors.get(i);
        tv_id.setText("ID: "+author.getId());
        tv_name.setText("name: "+author.getName());
        tv_address.setText("address: "+author.getAddress());
        tv_email.setText("email: "+author.getEmail());

        return view;
    }
}
