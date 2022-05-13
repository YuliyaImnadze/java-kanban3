import task.Task;
import task.TaskStatus;
import task.sub.Epic;
import task.sub.SubTask;
import service.*;

import java.util.Collections;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ManagerServiceInMemoryImpl managerServiceInMemory = new ManagerServiceInMemoryImpl();
        managerServiceInMemory.createTask(new Task("Учеба", TaskStatus.NEW, "Доделать тз"));
        managerServiceInMemory.createTask(new Task("Еда", TaskStatus.NEW, "Купить еды"));
        managerServiceInMemory.createEpic(new Epic("Квартира", TaskStatus.NEW, "Покупка квартиры"));
        managerServiceInMemory.createEpic(new Epic("Дача", TaskStatus.NEW, "Продажа дачи"));

        managerServiceInMemory.addSubTask(new SubTask("Подать объявление", TaskStatus.NEW, "Разместить объявление в газете", 3));
        managerServiceInMemory.addSubTask(new SubTask("Подписать договор", TaskStatus.NEW, "Заключить договор", 3));
        managerServiceInMemory.addSubTask(new SubTask("Найти покупателя", TaskStatus.NEW, "Подписать договор с риэлтором", 4));
        // managerServiceInMemory.deleteAllTasks();
        //     managerServiceInMemory.deleteSubtaskById(6);
        //     managerServiceInMemory.deleteAllEpics();
        //   managerServiceInMemory.deleteEpicById(3);
        // System.out.println(managerServiceInMemory.getTasks());
        //      managerServiceInMemory.updateTask(new Task("Учеба", TaskStatus.IN_PROGRESS, "Доделать тз"));
        managerServiceInMemory.updateEpic(new Epic("Квартира", TaskStatus.IN_PROGRESS, "Приобретение квартиры"));
        managerServiceInMemory.updateSubtask(new SubTask("Подать объявление", TaskStatus.DONE, "Разместить объявление в газете", 3));
        managerServiceInMemory.updateSubtask(new SubTask("Подписать договор", TaskStatus.DONE, "Заключить договор", 3));
        managerServiceInMemory.updateSubtask(new SubTask("Найти покупателя", TaskStatus.DONE, "Подписать договор с риэлтором", 4));
        managerServiceInMemory.addSubTask(new SubTask("Документы в МФЦ", TaskStatus.IN_PROGRESS, "Отдать документы в МФЦ", 3));
//        managerServiceInMemory.deleteSubtaskById(5);
//        managerServiceInMemory.updateSubtask(new SubTask("Подписать договор", TaskStatus.IN_PROGRESS, "Заключить договор", 3));
        System.out.println(managerServiceInMemory.getEpics());
        System.out.println(managerServiceInMemory.getAllSubtasks());
    }
}
