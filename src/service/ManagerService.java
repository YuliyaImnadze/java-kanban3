package service;

import task.Task;
import task.sub.Epic;
import task.sub.SubTask;

import java.util.Collection;
import java.util.List;

public interface ManagerService {

    void createTask(Task task);

    void updateTask(Task task);

    Collection<Task> getTasks();

    void createEpic(Epic epic);

    void updateEpic (Epic epic);

    Collection<Epic> getEpics();

    Collection<SubTask> getAllSubtasks();

    void addSubTask(SubTask subTask);

    void updateSubtask (SubTask subTask);

    List<Integer> getSubTask(int epicId);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks ();

    void deleteTaskById (int taskId);

    void deleteEpicById (int epicId);

    void deleteSubtaskById (int epicId);

    Task getTaskByID (int taskId);

    Epic getEpicByID (int epicId);

    SubTask getSubtaskByID (int subtaskId);
}


