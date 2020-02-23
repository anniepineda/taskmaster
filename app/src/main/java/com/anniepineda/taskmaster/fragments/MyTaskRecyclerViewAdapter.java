package com.anniepineda.taskmaster.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anniepineda.taskmaster.R;
import com.anniepineda.taskmaster.fragments.TaskFragment.OnListFragmentInteractionListener;
import com.anniepineda.taskmaster.models.Task;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Task} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> tasks;
    private final OnTaskSelectedListener mListener;

    public MyTaskRecyclerViewAdapter(List<Task> items, OnTaskSelectedListener listener) {
        tasks = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        // attach a listener to each task
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mListener.onTaskSelected(holder.task);

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Task taskAtPosition = this.tasks.get(position);
        holder.task = taskAtPosition;
        holder.taskTitle.setText(taskAtPosition.getTitle());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Task task;
        TextView taskTitle;

        public ViewHolder(View view) {
            super(view);
            this.taskTitle = itemView.findViewById(R.id.fragmentTaskTitle);
        }

    }

    public interface OnTaskSelectedListener{
        void onTaskSelected(Task task);
    }

}
