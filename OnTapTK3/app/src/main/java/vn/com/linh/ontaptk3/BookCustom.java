package vn.com.linh.ontaptk3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookCustom extends BaseAdapter {
    Context context;
    int layout;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView tvBookId = view.findViewById(R.id.tv_id);
        TextView tvBookTitle = view.findViewById(R.id.tv_title);
        TextView tvAuthorName = view.findViewById(R.id.tv_authorName);

        Book book = books.get(i);

        tvBookId.setText("id: " + book.getId());
        tvBookTitle.setText("title: " + book.getName());
        tvAuthorName.setText("author name: " + book.getAuthor().getName());

        return view;
    }
}
