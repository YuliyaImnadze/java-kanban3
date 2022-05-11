package task.sub;

import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subTasks = new ArrayList<>();

    public Epic(String name, TaskStatus status, String description) {
        super(name, status, description);
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void updateSubtaskInEpic(int subtaskId, SubTask subTask) {
        int idUpdSubtask = -1;
        for (int i = 0; i < this.subTasks.size(); i++) {
            if (subTasks.get(i).getId() == subtaskId) {
                idUpdSubtask = i;
            }
        }
        this.subTasks.remove(idUpdSubtask);
        this.subTasks.add(idUpdSubtask, subTask);
        }


    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setEpicStatus() {
        TaskStatus oldTaskStatus = this.getStatus();
        List<SubTask> subTasksUpd = this.getSubTasks();
        int counterDone = 0;
        int counterNew = 0;
        for (int i = 0; i < subTasksUpd.size(); i++) {
            switch (subTasksUpd.get(i).getStatus()) {
                case NEW:
                    counterNew++;
                    break;
                case IN_PROGRESS:
                    break;
                case DONE:
                    counterDone++;
                    break;
            }
        }

        if (subTasksUpd.size() == 0) {
            this.setStatus(TaskStatus.NEW);
        } else if (counterDone == subTasksUpd.size()) {
            this.setStatus(TaskStatus.DONE);
        } else if (counterNew == subTasksUpd.size())
        {
            this.setStatus(oldTaskStatus);
        }


    }


    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", subTasks=" + subTasks +
                '}';
    }
}