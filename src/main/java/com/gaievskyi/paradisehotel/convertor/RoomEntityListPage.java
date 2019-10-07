package com.gaievskyi.paradisehotel.convertor;

import com.gaievskyi.paradisehotel.entity.RoomEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public class RoomEntityListPage {
    List<RoomEntity> content;
    Pageable pageable;
    public RoomEntityListPage(List<RoomEntity> content, Pageable pageable) {
        this.content = content;
        this.pageable = pageable;
    }
    public PageImpl<RoomEntity> getPage(){
        //Validation
        if(content == null || pageable == null)
            return new PageImpl<RoomEntity>(content);
        //Sorting
        if (pageable.getSort() != null){
            System.out.println(pageable.getSort().toString());
            Collections.sort(content, (o1, o2) -> {
                String property = pageable.getSort().toString().split(",")[0].split(":")[0].toLowerCase();
                String direction = pageable.getSort().toString().split(",")[0].split(":")[1].trim().toLowerCase();
                switch (property){
                    case "price" : {
                        if(direction.equals("asc"))
                            return Double.parseDouble(o1.getPrice()) > Double.parseDouble(o2.getPrice()) ? 1 : -1;
                        else
                            return Double.parseDouble(o1.getPrice()) < Double.parseDouble(o2.getPrice()) ? 1 : -1;
                    }
                    case "roomnumber" : {
                        if(direction.equals("asc"))
                            return o1.getRoomNumber() > o2.getRoomNumber() ? 1 : -1;
                        else
                            return o1.getRoomNumber() < o2.getRoomNumber() ? 1 : -1;
                    }
                    case "roomtype" : {
                        if(direction.equals("asc"))
                            return o1.getRoomType().compareTo(o2.getRoomType()) > 0 ? 1 : -1;
                        else
                            return o1.getRoomType().compareTo(o2.getRoomType()) < 0 ? 1 : -1;
                    }
                }
                return 0;
            });
        }
        //Sorting End
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > content.size() ? content.size() : (start + pageable.getPageSize());
        return new PageImpl<>(content.subList(start, end), pageable, content.size());
    }

}
