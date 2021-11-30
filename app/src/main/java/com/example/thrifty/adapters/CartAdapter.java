package com.example.thrifty.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;
import com.example.thrifty.CartActivity;
import com.example.thrifty.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    public interface DeleteItem{
        void onDeleteItem(Product product);
    }

    private DeleteItem deleteItem;
    private Fragment fragment;
    List<Product> items = new ArrayList<>();

    Context context;
    public CartAdapter(List<Product> items, CartActivity cartActivity){
        this.items = items;
    }

    public CartAdapter(Fragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.product = items.get(position);
        TextView txtName = holder.itemView.findViewById(R.id.txtName);
        TextView txtPrice = holder.itemView.findViewById(R.id.price);
        ImageButton imageButton = holder.itemView.findViewById(R.id.deleteBtn);

        txtName.setText(holder.product.getTitle());
        txtPrice.setText(holder.product.getPrice()+"$");

        imageButton.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("Deleting......")
                    .setMessage("Are Sure You Want To Remove This Item? ")
                    .setNegativeButton("NO", (dialogInterface, i) -> {

                    }).setPositiveButton("YES", (dialogInterface, i) -> {
//Here is the deleting from the cart
                        try{
                            deleteItem = (DeleteItem) fragment;
                        }catch (Exception e){
                            e.getMessage();
                        }

                    });
            builder.create().show();
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Product> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Product product;
        public View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
