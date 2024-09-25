package com.example.springtodos.repositories;

import com.example.springtodos.tasks.models.Task;
import com.example.springtodos.tasks.repositories.TaskRepository;
import com.example.springtodos.users.models.User;
import com.example.springtodos.users.repositories.UserRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Test 1: Test Task Fetch Data")
    public void testFetchData(){
        /*Test data retrieval*/
        Task taskA = taskRepository.findTaskByName("Test Task");
        assertNotNull(taskA);
        assertEquals("Test Task", taskA.getName());
        /*Get all tasks, list should only have two*/
        Iterable<Task> tasks = taskRepository.findAll();
        int count = 0;
        for(Task p : tasks) {
            count++;
        }
        assertEquals(count, 2);
    }

    @Test
    @DisplayName("Test 2: Create and Find Task")
    public void testCreateTask() {
        User user = new User("Test User", "testuser@email.com", "pass", null, null);
        assertNull(user.getId());
        this.userRepository.save(user);
        Task task = new Task("Test Task 3", "Test Description 3", false, user);
        assertNull(task.getId());
        this.taskRepository.save(task);
        assertNotNull(task.getId());
        Task task2 = taskRepository.findTaskByName("Test Task 3");
        assertNotNull(task2);
        assertEquals("Test Task 3", task2.getName());
    }
}
