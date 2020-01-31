package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void trelloServiceTest(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "listId1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("name", "url", "listid");
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("id", "name", false);
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("id", "name", trelloList);
        trelloBoardDtoList.add(trelloBoardDto);

        Mockito.when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        Mockito.when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        Mockito.when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto readCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertEquals("url", readCreatedTrelloCardDto.getName());
        Assert.assertTrue(!trelloBoardDtos.isEmpty());


    }
}
