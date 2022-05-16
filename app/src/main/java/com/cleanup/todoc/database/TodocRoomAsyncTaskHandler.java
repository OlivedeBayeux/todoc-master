package com.cleanup.todoc.database;

import android.os.AsyncTask;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.ui.MainActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TodocRoomAsyncTaskHandler {

    // ADD TASK ASYNCHRONOUS WAY
    public static class AddTaskAsync extends AsyncTask<Task, Void, Void> {
        private WeakReference<MainActivity> activityReference;

        public AddTaskAsync(MainActivity mainActivity){
            activityReference = new WeakReference<>(mainActivity);
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            MainActivity activity = activityReference.get();
            if (activity != null && !activity.isFinishing()) {
                for (Task task : tasks) {
                    task.setId(MainActivity.sTodocRoomDatabase.roomDao().addTask(task));
                    activity.tasks.add(task);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity activity = activityReference.get();
            if (activity != null && !activity.isFinishing()) {
                activity.updateTasks();
            }
        }
    }

    // DELETE TASK ASYNCHRONOUS WAY
    public static class DeleteTaskASync extends AsyncTask<Task, Void, Void> {
        private WeakReference<MainActivity> activityReference;

        public DeleteTaskASync(MainActivity mainActivity) {
            activityReference = new WeakReference<>(mainActivity);
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            MainActivity activity = activityReference.get();
            if (activity != null && !activity.isFinishing()) {
                for (Task task : tasks) {
                    MainActivity.sTodocRoomDatabase.roomDao().deleteTask(task);
                    activity.tasks.remove(task);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity activity = activityReference.get();
            if (activity != null && !activity.isFinishing()) {
                activity.updateTasks();
            }
        }
    }

    // GET ALL TASKS ASYNCHRONOUS WAY
    public static class GetTasksAsync extends AsyncTask<Void, Void, Void> {
        private WeakReference<MainActivity> activityReference;

        public GetTasksAsync(MainActivity mainActivity) {
            activityReference = new WeakReference<>(mainActivity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MainActivity activity = activityReference.get();
            if (activity != null && !activity.isFinishing()) {
                activity.tasks = (ArrayList<Task>) MainActivity.sTodocRoomDatabase.roomDao().getTasks();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity activity = activityReference.get();
            if (activity != null && !activity.isFinishing()) {
                activity.updateTasks();
            }
        }
    }
}
