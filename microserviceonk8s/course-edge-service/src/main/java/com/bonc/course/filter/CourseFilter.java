package com.bonc.course.filter;

import com.bonc.thrift.dto.UserDTO;
import com.bonc.user.client.LoginFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class CourseFilter extends LoginFilter {

    @Value("${user.edge.service.addr}")
    private String userEdgeServiceAddr;

    @Override
    protected String userEdgeServiceAddr() {
        return userEdgeServiceAddr;
    }

    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {

        request.setAttribute("user", userDTO);
    }

}
