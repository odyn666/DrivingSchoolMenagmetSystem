package com.github.odyn666.appauth.view;

import com.github.odyn666.appauth.dto.StudentEntityDto;
import com.github.odyn666.appauth.service.StudentDTOService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "/students", layout = MainLayout.class)
public class CustomerView extends Div {

    private final StudentDTOService service;

    public CustomerView(StudentDTOService service) {
        this.service = service;
        createGrid();
    }

    public void createGrid () {
            Grid<StudentEntityDto> grid = new Grid<>(StudentEntityDto.class, false);

            Grid.Column<StudentEntityDto> firstname = grid.addColumn(StudentEntityDto::getFirstName);
            Grid.Column<StudentEntityDto> lastname = grid.addColumn(StudentEntityDto::getLastName);
            Grid.Column<StudentEntityDto> email = grid.addColumn(StudentEntityDto::getEmail);
            Grid.Column<StudentEntityDto> phoneNumber = grid.addColumn(StudentEntityDto::getPhoneNumber);
            Grid.Column<StudentEntityDto> hoursLeft = grid.addColumn(StudentEntityDto::getHoursLeft);
            Grid.Column<StudentEntityDto> hoursDriven = grid.addColumn(StudentEntityDto::getHoursDriven);

            List<StudentEntityDto> students = service.getStudents();

            grid.setItems(students);
            add(grid);
        }


}

