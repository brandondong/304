package web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class QueryController {

	static class Test {
		String prop1 = "1";
		String prop2 = "2";

		public String getProp1() {
			return prop1;
		}

		public String getProp2() {
			return prop2;
		}
	}

	@RequestMapping("/test")
	public Test testQuery() {
		return new Test();
	}

}
