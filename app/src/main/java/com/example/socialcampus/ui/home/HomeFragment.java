package com.example.socialcampus.ui.home;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.socialcampus.R;
import com.example.socialcampus.ui.group.PostCard;
import com.example.socialcampus.ui.group.PostListAdapter;

import java.util.LinkedList;

public class HomeFragment extends Fragment {

    private final LinkedList<GroupBoxCard> gCardList = new LinkedList<>();
    private RecyclerView gRecyclerView;
    private GroupBoxAdapter gAdapter;
    private final LinkedList<PostCard> postCardList = new LinkedList<>();
    private RecyclerView postRecyclerView;
    private PostListAdapter postAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        gRecyclerView = root.findViewById(R.id.recyclerView_group);
        gAdapter = new GroupBoxAdapter(getContext(), gCardList);
        gRecyclerView.setAdapter(gAdapter);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            gRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

        postRecyclerView = root.findViewById(R.id.group_post_recycler_home);
        postAdapter = new PostListAdapter(getContext(), postCardList);
        postRecyclerView.setAdapter(postAdapter);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        initializeData();

        return root;
    }

    private void initializeData() {

        //https://stackoverflow.com/questions/29819204/could-android-store-drawable-ids-like-an-integer-array
        TypedArray tArray = getResources().obtainTypedArray(
                R.array.group_pictures);
        int count = tArray.length();
        int[] ids = new int[count];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = tArray.getResourceId(i, 0);
        }
        tArray.recycle();

        int[] gruppeBilder = ids;
        String[] gruppeTittel = getResources().getStringArray(R.array.group_title);
        String[] gruppeAntMeldemmer = getResources().getStringArray(R.array.group_member_count);
        String[] gruppeAntPoster = getResources().getStringArray(R.array.group_post_count);

        gCardList.clear();
        for (int i = 0; i < 10; i++){
            gCardList.addLast(new GroupBoxCard(gruppeBilder[i], gruppeTittel[i], gruppeAntMeldemmer[i], gruppeAntPoster[i]));
        }

        postCardList.clear();
        for (int i=0; i<10; i++) {
            postCardList.add(new PostCard(getString(R.string.socialcampus), getString(R.string.username), getString(R.string.placeholder_text)));
        }

        gAdapter.notifyDataSetChanged();
        postAdapter.notifyDataSetChanged();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

}