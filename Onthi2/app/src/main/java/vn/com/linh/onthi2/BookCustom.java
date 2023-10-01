package vn.com.linh.onthi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookCustom extends BaseAdapter {
    private Context context;
    private int layout;
    ArrayList<Book> books;

    public BookCustom(Context context, int layout, ArrayList<Book> books) {
        this.context = context;
        this.layout = layout;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
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

        TextView tv_Id=view.findViewById(R.id.tv_id);
        TextView tv_name=view.findViewById(R.id.tv_Name);
        TextView tv_id_author=view.findViewById(R.id.tv_idAuthor);

        Book book=books.get(i);
        tv_Id.setText("id: "+book.getId());
        tv_name.setText("name: "+book.getName());
        tv_id_author.setText("id_author: "+book.getIdAuthor());
        return view;
    }
}
