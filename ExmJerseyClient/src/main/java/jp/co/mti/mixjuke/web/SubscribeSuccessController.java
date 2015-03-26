/**
 * 
 */
package jp.co.mti.mixjuke.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Xuan Nguyen
 * 
 */
@Controller
@RequestMapping("/serviceRegistSuccess")
public class SubscribeSuccessController extends AbstractWeb {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView subscribeSuccess(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        if (uid == null || USER_NOT_LOGIN.equals(uid)) {
            return new ModelAndView("login");
        }
        return new ModelAndView("subscribe-success");
    }

}
