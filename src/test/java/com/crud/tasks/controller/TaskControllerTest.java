package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasksTest() throws Exception {
        //Given
        List<TaskDto> taskList = new ArrayList<>();
        taskList.add(new TaskDto(1L, "test task", "test content"));

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskList);

        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test task")))
                .andExpect(jsonPath("$[0].content", is("test content")));
    }

    @Test
    public void shouldGetTaskTest() throws Exception {
        //Given
        TaskDto task = new TaskDto(1L, "test task", "test content");
        Task task1 = new Task(1L, "test task", "test content");


        when(service.getTaskById(anyLong())).thenReturn(java.util.Optional.of(task1));
        when(taskMapper.mapToTaskDto(task1)).thenReturn(task);

        // When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test task")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void shouldDeleteTaskTest() throws Exception {
        // When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shoulUpdateTaskTest() throws Exception {
        //Given
        TaskDto task = new TaskDto(1L, "test task", "test content");

        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(any(TaskDto.class))))).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        // When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test task")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void shoulCreateTaskTest() throws Exception {
        //Given
        TaskDto task = new TaskDto(1L, "test task", "test content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        // When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }







}