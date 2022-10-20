package notice.jk;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;


@Component
public class NoticePageVO extends PageVO {
	//jk가 올림
	private List<NoticeVO> list;

	public List<NoticeVO> getList() {
		return list;
	}

	public void setList(List<NoticeVO> list) {
		this.list = list;
	}
	
	
	
}
