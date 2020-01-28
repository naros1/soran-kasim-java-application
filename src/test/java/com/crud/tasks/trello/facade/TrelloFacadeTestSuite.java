package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloFacadeTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void TrelloMapperTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test", false);
        TrelloList trelloList2 = new TrelloList("2", "test2", false);
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(trelloList1);
        trelloList.add(trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1", "test_Board", trelloList);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        TrelloCard card = new TrelloCard("test_card", "test_card_mapper", "test_pos", "1");
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        trelloBoardsDto.get(0).getLists().add(new TrelloListDto("3", "test3", false));
        List<TrelloBoard> trelloBoardsTested = trelloMapper.mapToBoards(trelloBoardsDto);
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(card);
        TrelloCard cardTested = trelloMapper.mapToCard(new TrelloCardDto("tested_card", trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId()));
        //Then
        assertEquals(trelloBoardsTested.get(0).getLists().size(), 3 );
        assertEquals(trelloBoardsTested.get(0).getName(), "test_Board");
        assertEquals(trelloBoardsTested.get(0).getId(), "1");
        assertEquals(trelloBoardsTested.get(0).getLists().get(2).getName(), "test3");
        assertEquals(cardTested.getName(), "tested_card");
        assertEquals(cardTested.getDescription(), "test_card_mapper");
        assertEquals(cardTested.getPos(), "test_pos");
        assertEquals(cardTested.getListId(), "1");


    }

}
