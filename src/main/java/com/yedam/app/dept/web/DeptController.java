package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {
	
	private final DeptService deptService;

	/* 롬복의 @RequiredArgsConstructor 애노테이션을 사용하면 final이 있는 필드를 포함한 생성자를 만들어주기 때문에 생략 가능하다  
	 * @Autowired // 생성자가 하나면 생략 가능 
	 * public DeptController(DeptService deptService) { 
	 * 		this.deptService = deptService; 
	 * }
	 */
	// 전체조회
	@GetMapping
	public String deptList(Model model) {
		// 기능수행
		List<DeptVO> deptList = deptService.deptList();
		// 클라이언트에 전달할 데이터 담기
		model.addAttribute("deptList", deptList);
		// 데이터를 출력할 페이지 결정
		return "dept/list";
	}
	
	// 단건조회
	@GetMapping("/{deptId}")
	public String deptInfo(@PathVariable Long deptId, Model model) {
		DeptVO findVO = deptService.deptInfo(deptId);
		model.addAttribute("deptInfo", findVO);
		return "dept/info";
	}
	
	// 등록 - 페이지
	@GetMapping("/save")
	public String deptInsertForm() {
		return "dept/saveForm";
	}
	
	// 등록 - 처리(연산, submit)
	@PostMapping("/save")
	public String deptInsertProcess(Model model, DeptVO deptVO) {
		int dId = deptService.deptInsert(deptVO);
		String url = null;
		
		if(dId > -1) {
			// 정상 등록된 경우 등록된 부서 정보 조회페이지로 이동
			url = "redirect:dept/" + dId;
		} else {
			// 등록이 되지않은 경우 부서 목록 페이지로 이동
			url = "redirect:dept";
		}
		return url;
	}
	
	// 수정 - 페이지
	@GetMapping("/{deptId}/edit")
	public String deptUpdateForm(@PathVariable Long deptId, Model model) {
		DeptVO deptVO = deptService.deptInfo(deptId);
		model.addAttribute("dept", deptVO);
		return "dept/editForm";
	}
	
	// 수정 - 처리
	@PostMapping("/{deptId}/edit")
	public String deptUpdate(@PathVariable Long deptId, @ModelAttribute DeptVO deptVO) {
		deptService.deptUpdate(deptVO);
		return "redirect:/dept/{deptId}";
	}
	
	// 삭제
	// 배운거 쓰려고 내 마음대로 해봄 
	@PostMapping("/{deptId}/remove")
	public String deptDelete(@PathVariable Long deptId, RedirectAttributes ra) {
		DeptVO findVO = deptService.deptInfo(deptId);
		//ex) {"deptid", 10}
		Map<String, Object> result = deptService.deptDelete(findVO);
		ra.addAttribute("deptId", result.get("deptid"));
		ra.addAttribute("status", true);
		// 예상경로 /dept?deptId=?&status=true
		return "redirect:/dept";
	}
	
	
}
