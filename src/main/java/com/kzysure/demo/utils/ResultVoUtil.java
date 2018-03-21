package com.kzysure.demo.utils;

import com.kzysure.demo.VO.ProductVO;
import com.kzysure.demo.VO.ResultVO;
import java.util.List;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResultVoUtil {
  public static ResultVO success(Object object){
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(0);
    resultVO.setMsg("请求成功");
    resultVO.setData(object);
    return resultVO;



  }
  public static ResultVO success(){
    return success(null);
  }
  public static ResultVO error(Integer code, String msg){
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(1);
    resultVO.setMsg("请求失败");
    return resultVO;
  }

}
