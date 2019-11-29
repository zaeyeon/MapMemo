package com.example.findintermediateapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MemoListAdapter extends RecyclerView.Adapter<MemoListAdapter.ViewHolder> {

    Context context;
    private ArrayList<MemoListItem> memoData = null;

    // 생성자에서 데이터 리스트 객체를 전달받음
    MemoListAdapter(ArrayList<MemoListItem> list) { memoData = list; }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @Override
    public MemoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.memo_list_item, parent, false);

        MemoListAdapter.ViewHolder vh = new MemoListAdapter.ViewHolder(view);
        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(MemoListAdapter.ViewHolder holder, int position) {
        MemoListItem item = memoData.get(position);
        Bitmap bitmap = null;
        holder.tv_memoContent.setText(item.getMemoContent());
        holder.tv_memoImageCount.setText(String.valueOf(item.getMemoImageCount()));
        Uri imageUri = Uri.parse(item.getMemoFirstImage());
        try {
         bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        holder.iv_memoImage.setImageBitmap(bitmap);

    }

    // getItemCount() - 전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return (null != memoData) ? memoData.size() : 0;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_memoContent;
        TextView tv_memoImageCount;
        ImageView iv_memoImage;

        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            tv_memoContent = itemView.findViewById(R.id.memo_item_content);
            tv_memoImageCount = itemView.findViewById(R.id.memo_item_imageCount);
            iv_memoImage = itemView.findViewById(R.id.memo_item_image);
        }
    }


}