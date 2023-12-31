package id.co.indivara.jdt12.hotell;
import id.co.indivara.jdt12.hotell.controller.RoomController;
import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.mapper.MapperConvert;
import id.co.indivara.jdt12.hotell.service.RoomService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class RoomTest {
    @Autowired
    private MockMvc mockMvc;//MockMvc fasilitas untuk membantu proses testing di Junit
    @Autowired
    RoomService roomService;
    @Autowired
    RoomController roomController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }
    @Test
    public void getAllRoom() throws Exception {
        List<Room> roomChecker = roomService.getAllRoom();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rooms")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    List<Room> rooms = MapperConvert.getAllData(result.getResponse().getContentAsString(), Room.class);
                    Assertions.assertNotNull(rooms);
                    Assertions.assertEquals(roomChecker.get(0).getRoomType(), rooms.get(0).getRoomType());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roomId").isNotEmpty());
    }
}
