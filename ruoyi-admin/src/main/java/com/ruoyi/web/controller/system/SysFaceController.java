package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.impl.GsonUtils;
import com.ruoyi.system.service.impl.LoginService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user")
public class SysFaceController extends BaseController
{
    @Autowired
    private LoginService loginService;

    //人脸新增
    @PreAuthorize("@ss.hasPermi('system:user:registerFace')")
    @RequestMapping("/registerFace")
    @ResponseBody
    public String registerFace(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64, Model model, HttpServletRequest request) throws IOException {
        Map<String, Object> searchface = loginService.registerFace(imagebast64);
        if (searchface == null || searchface.get("user_id") == null) {
            String flag = "fail";
            String s = GsonUtils.toJson(flag);
            return s;
        }
        String user_id = searchface.get("user_id").toString();
        String score = searchface.get("score").toString().substring(0, 2);
        int i = Integer.parseInt(score);
        if (i > 80) {
            model.addAttribute("userinf", user_id);
            HttpSession session = request.getSession();
            session.setAttribute("userinf", user_id);
            System.out.println("存入session");
        }


        System.out.println(searchface);
        String s = GsonUtils.toJson(searchface);
        return s;
    }

}
