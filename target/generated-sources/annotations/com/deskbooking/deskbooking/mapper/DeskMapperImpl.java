package com.deskbooking.deskbooking.mapper;

import com.deskbooking.deskbooking.dto.DeskDTO;
import com.deskbooking.deskbooking.model.Desk;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-04T16:47:39+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class DeskMapperImpl implements DeskMapper {

    @Override
    public DeskDTO toDeskDTO(Desk desk, List<LocalDateTime> freeHours) {
        if ( desk == null && freeHours == null ) {
            return null;
        }

        Desk desk1 = null;
        desk1 = desk;
        List<LocalDateTime> freeHours1 = null;
        List<LocalDateTime> list = freeHours;
        if ( list != null ) {
            freeHours1 = new ArrayList<LocalDateTime>( list );
        }

        DeskDTO deskDTO = new DeskDTO( desk1, freeHours1 );

        return deskDTO;
    }
}
