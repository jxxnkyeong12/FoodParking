package enter.jk;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class EnterPageVO extends PageVO {
	private List<EnterVO> list;

	public List<EnterVO> getList() {
		return list;
	}

	public void setList(List<EnterVO> list) {
		this.list = list;
	}
}
