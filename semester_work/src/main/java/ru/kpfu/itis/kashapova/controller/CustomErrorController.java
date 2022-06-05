package ru.kpfu.itis.kashapova.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            logger.info(String.valueOf(statusCode));
            logger.info(request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString());

            model.addAttribute("errorNum", status);
            switch (statusCode) {
                case 400:
                    model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
                    logger.warn(request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString());
                    break;
                case 403:
                    model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
                    logger.warn("Have not access");
                    ExceptionHandlerController.accessDenied();
                    return "error_page/FORBIDDEN";
                case 404:
                    model.addAttribute("errorMessage", "The page you are looking for was not found.");
                    logger.warn("The page was not found.");
                    ExceptionHandlerController.notFound();
                    return "error_page/NOT_FOUND";
                case 500:
                    model.addAttribute("errorMessage", "Error inside the server.");
                    logger.warn("Error inside the server.");
                    ExceptionHandlerController.serverError();
                    return "error_page/INTERNAL_SERVER_ERROR";
                default:
                    model.addAttribute("errorNum", "Error");
                    model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
                    logger.warn(request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString());
                    break;
            }
        }
        return "error_page/error";
    }

    @PostMapping("/error")
    public String postHandleError(HttpServletRequest request, Model model) {
        return handleError(request, model);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
