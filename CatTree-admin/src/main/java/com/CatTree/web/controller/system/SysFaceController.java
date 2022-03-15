package com.CatTree.web.controller.system;

import com.CatTree.common.annotation.Log;
import com.CatTree.common.constant.UserConstants;
import com.CatTree.common.core.controller.BaseController;
import com.CatTree.common.core.domain.AjaxResult;
import com.CatTree.common.core.domain.entity.SysRole;
import com.CatTree.common.core.domain.entity.SysUser;
import com.CatTree.common.core.page.TableDataInfo;
import com.CatTree.common.enums.BusinessType;
import com.CatTree.common.utils.SecurityUtils;
import com.CatTree.common.utils.StringUtils;
import com.CatTree.common.utils.poi.ExcelUtil;
import com.CatTree.system.domain.SchoolAttendance;
import com.CatTree.system.domain.SchoolAttendanceDetail;
import com.CatTree.system.domain.SysUserFace;
import com.CatTree.system.service.*;
import com.CatTree.system.service.impl.GsonUtils;
import com.CatTree.system.service.impl.LoginService;
import com.CatTree.system.service.impl.SysUserFaceServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author CatTree
 */
@RestController
@RequestMapping("/system/faceUser")
public class SysFaceController extends BaseController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ISysUserFaceService sysUserFaceService;
    @Autowired
    private ISchoolAttendanceDetailService schoolAttendanceDetailService;
    @Autowired
    private ISchoolAttendanceService schoolAttendanceService;

    //人脸新增
    @RequestMapping("/registerFace")
    @ResponseBody
    public AjaxResult registerFace(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64, Model model, HttpServletRequest request) throws IOException {
        AjaxResult ajaxResult;
        try {
            SysUserFace sysUserFace = new SysUserFace();
            sysUserFace.setUserId(SecurityUtils.getUserId());
            List<SysUserFace> sysUserFaces = sysUserFaceService.selectSysUserFaceList(sysUserFace);
            if (CollectionUtils.isEmpty(sysUserFaces)) {
                String faceToken = loginService.registerFace(imagebast64);
                sysUserFace.setFaceToken(faceToken);
                sysUserFaceService.insertSysUserFace(sysUserFace);
            }
            ajaxResult = AjaxResult.success();
        } catch (Exception e) {
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping(value = "/{faceToken}")
    public AjaxResult getInfo() {
        SysUserFace sysUserFace = new SysUserFace();
        sysUserFace.setUserId(SecurityUtils.getUserId());
        List<SysUserFace> sysUserFaces = sysUserFaceService.selectSysUserFaceList(sysUserFace);
        if (CollectionUtils.isEmpty(sysUserFaces)) {
            return AjaxResult.error("未查询到人脸采样信息，请至个人中心录入人脸采样信息！");
        }

        return AjaxResult.success();
    }

    //人脸识别
    @RequestMapping("/signIn")
    @ResponseBody
    @Transactional
    public AjaxResult searchFace(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64,
                                 @RequestParam(name = "id") Long id,
                                 Model model,
                                 HttpServletRequest request) throws IOException {
        AjaxResult ajaxResult;
        try {
            SysUserFace sysUserFace = new SysUserFace();
            sysUserFace.setUserId(SecurityUtils.getUserId());
            List<SysUserFace> sysUserFaces = sysUserFaceService.selectSysUserFaceList(sysUserFace);
            if (CollectionUtils.isEmpty(sysUserFaces)) {
                ajaxResult = AjaxResult.error("未查询到人脸采样信息，请至个人中心录入人脸采样信息！");
            } else {
                String userId = loginService.searchface(imagebast64);
                SysUserFace sysUserFace1 = sysUserFaces.get(0);
                if (StringUtils.isNotBlank(userId) && userId.equals(String.valueOf(sysUserFace1.getUserId()))) {
                    SchoolAttendanceDetail schoolAttendanceDetail = schoolAttendanceDetailService.selectSchoolAttendanceDetailById(id);
                    SchoolAttendance schoolAttendance = schoolAttendanceService.selectSchoolAttendanceByAttendanceId(schoolAttendanceDetail.getAttendanceId());
                    Date date = new Date();
                    boolean b = schoolAttendance.getCourseStart().getTime() < date.getTime();
                    schoolAttendanceDetail.setStatus("已签到");
                    schoolAttendanceDetail.setSignTime(date);
                    schoolAttendanceDetail.setIsLate(b ? "是" : "否");
                    schoolAttendanceDetailService.updateSchoolAttendanceDetail(schoolAttendanceDetail);

                    Long signIn = schoolAttendance.getSignIn();
                    signIn = signIn + new Long(1);
                    Long noSignIn = schoolAttendance.getNoSignIn();
                    noSignIn = noSignIn - new Long(1);
                    schoolAttendance.setSignIn(signIn);
                    schoolAttendance.setNoSignIn(noSignIn);
                    schoolAttendanceService.updateSchoolAttendance(schoolAttendance);
                    ajaxResult = AjaxResult.success();
                } else {
                    ajaxResult = AjaxResult.error("人脸识别失败！");
                }
            }
        } catch (Exception e) {
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

}
