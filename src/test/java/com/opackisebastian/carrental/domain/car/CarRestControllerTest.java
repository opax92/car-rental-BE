package com.opackisebastian.carrental.domain.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opackisebastian.carrental.domain.car.exception.ResourceAlreadyExistsException;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarRestController.class)
class CarRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    private final static String CARD_ID = "123e4567-e89b-12d3-a456-426614174000";

    @Test
    void shouldReturnCarDataAndHttpOkWhenServiceFoundCar() throws Exception {
        when(carService.findCarById(CARD_ID)).thenReturn(Optional.of(Car.builder()
                .id("123e4567-e89b-12d3-a456-426614174000")
                .color("RED")
                .model("3")
                .yearModel(2019)
                .type("sedan")
                .brand("mazda")
                .build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/" + CARD_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("color").value("RED"))
                .andExpect(jsonPath("model").value("3"))
                .andExpect(jsonPath("yearModel").value("2019"))
                .andExpect(jsonPath("type").value("sedan"))
                .andExpect(jsonPath("brand").value("mazda"))
                .andReturn();
    }

    @Test
    public void te() {
        boolean palindrom = palindrom("kobyłam5amałybok".toCharArray());

        System.out.println(palindrom);
    }

    //kajak
    private boolean palindrom(char[] text) {
        for (int i = 0; i < text.length; ++i) {
            int lastElementToCompare = text.length - 1 - i;
            if(lastElementToCompare < i) {
                break;
            }
            if (text[i] != text[lastElementToCompare]) {
                return false;
            }
        }

        return true;
    }

    @Test
    void shouldReturnConflictWhenResourceAlreadyExists() throws Exception {
        CarDTOPostRequest carDTOPostRequest = CarDTOPostRequest.builder()
                .color("RED")
                .model("3")
                .yearModel(2019)
                .type("sedan")
                .brand("mazda")
                .build();

        when(carService.addNewCar(any())).thenReturn(Either.left(new ResourceAlreadyExistsException()));

        mockMvc.perform(MockMvcRequestBuilders.post("/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDTOPostRequest)))
                .andExpect(status().isConflict())
                .andReturn();
    }

    @Test
    void shouldReturnCreatedWhenResourceCorrectCreated() throws Exception {
        Car car = Car.builder()
                .id(CARD_ID)
                .color("RED")
                .model("3")
                .yearModel(2019)
                .type("sedan")
                .brand("mazda")
                .build();

        when(carService.addNewCar(any())).thenReturn(Either.right(car));

        mockMvc.perform(MockMvcRequestBuilders.post("/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("color").value("RED"))
                .andExpect(jsonPath("model").value("3"))
                .andExpect(jsonPath("yearModel").value("2019"))
                .andExpect(jsonPath("type").value("sedan"))
                .andExpect(jsonPath("brand").value("mazda"))
                .andReturn();
    }

    @Test
    void shouldReturnNotFoundWhenServiceNotFoundCar() throws Exception {
        when(carService.findCarById(CARD_ID)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/" + CARD_ID))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void shouldDeleteResource() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cars/" + CARD_ID))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}