package service;

import task.Task;
import task.sub.Epic;
import task.sub.SubTask;

import java.util.*;

public class ManagerServiceInMemoryImpl implements ManagerService {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, SubTask> subTasks = new HashMap<>();

    private int i = 0;

    @Override
    public void createTask(Task task) {
        task.setId(++i);
        tasks.put(task.getId(), task);
    }

    @Override
    public Collection<Task> getTasks() {
        return tasks.values();
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(++i);
        epics.put(epic.getId(), epic);
    }

    @Override
    public Collection<Epic> getEpics() {
        return epics.values();
    }

    @Override
    public void addSubTask(SubTask subTask) {
        int subTaskId = ++this.i;
        subTask.setId(subTaskId);
        subTasks.put(subTaskId, subTask);
        int epicIdOfSubTask = subTask.getEpicId();
        Epic epic = epics.get(epicIdOfSubTask);
        if (epic != null) {
            epic.addSubTask(subTask);
        }
    }

    @Override
    public List<SubTask> getSubTask(int epicId) {
        Epic epic = epics.get(epicId);
        return epic.getSubTasks();
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteSubtaskById(int subtaskId) {
       subTasks.remove(subtaskId);
    }

    @Override
    public void deleteAllSubtasks() {
        subTasks.clear();
    }


    @Override
    public void deleteAllEpics() {
        Collection<Epic> allEpics = this.getEpics();
        Iterator<Epic> iterator = allEpics.iterator();
        while (iterator.hasNext()) {
            Epic e1 = iterator.next();
            this.deleteSubtaskById(e1.getId());
        }
        epics.clear();
    }

    @Override
    public void updateTask(Task task) {
        int idUpdTask = -1;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (Objects.equals(entry.getValue().getName(), task.getName()))
                idUpdTask = entry.getValue().getId();
        }
        if (idUpdTask >= 0) {
            task.setId(idUpdTask);
            tasks.put(idUpdTask, task);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        int idUpdEpic = -1;
        for (Map.Entry<Integer, Epic> entry : epics.entrySet()) {
            if (Objects.equals(entry.getValue().getName(), epic.getName()))
                idUpdEpic = entry.getValue().getId();
        }
        if (idUpdEpic >= 0) {
            epic.setId(idUpdEpic);

            List<SubTask> subTasksMod = epics.get(idUpdEpic).getSubTasks();
            for (int i = 0; i < subTasksMod.size(); i++)
            {
                SubTask subTaskModified = new SubTask(subTasksMod.get(i).getName(), epic.getStatus(),subTasksMod.get(i).getDescription(), epic.getId());
                subTaskModified.setId(subTasksMod.get(i).getId());
                subTasksMod.set(i, subTaskModified);
                subTasks.put(subTaskModified.getId(), subTaskModified);
                epic.addSubTask(subTaskModified);
            }
            epics.put(idUpdEpic, epic);
        }
    }

    @Override
    public void updateSubtask(SubTask subTask) {
        int idUpdSubtask = -1;
        for (Map.Entry<Integer, SubTask> entry : subTasks.entrySet()) {
            if (Objects.equals(entry.getValue().getName(), subTask.getName()))
                idUpdSubtask = entry.getValue().getId();
        }
        if (idUpdSubtask >= 0) {
            subTask.setId(idUpdSubtask);
            subTasks.put(idUpdSubtask, subTask);
            Epic ep1 = epics.get(subTask.getEpicId());
            ep1.updateSubtaskInEpic(subTask.getId(),subTask);
            ep1.setEpicStatus();
            "".isEmpty();
        }

    }

    @Override
    public void deleteTaskById (int taskId)
    {
        tasks.remove(taskId);
    }

    @Override
    public void deleteEpicById (int epicId)
    {
        epics.remove(epicId);

        ArrayList<Integer> idDelSubtasks = new ArrayList<>();
        for (Map.Entry<Integer, SubTask> entry : subTasks.entrySet()) {
            if (entry.getValue().getEpicId() == epicId)
                idDelSubtasks.add(entry.getValue().getId());
        }

        for (Integer idDelSubtask : idDelSubtasks) {
            subTasks.remove(idDelSubtask);
        }

    }

}



