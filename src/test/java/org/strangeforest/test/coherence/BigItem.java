package org.strangeforest.test.coherence;

import java.io.*;

import static java.lang.String.*;

public class BigItem implements Serializable {

	private long id;
	private String name;
	private String desc1;
	private String desc2;
	private String desc3;
	private String desc4;
	private String desc5;
	private String desc6;
	private String desc7;
	private String desc8;
	private String desc9;
	private String desc10;
	private int int1;
	private int int2;
	private int int3;
	private int int4;
	private int int5;
	private int int6;
	private int int7;
	private int int8;
	private int int9;
	private int int10;
	private long long1;
	private long long2;
	private long long3;
	private long long4;
	private long long5;
	private long long6;
	private long long7;
	private long long8;
	private long long9;
	private long long10;

	public BigItem() {}

	public BigItem(long id, String name) {
		this.id = id;
		this.name = name;
		String desc = "Description: " + name;
		this.desc1 = desc + "_1";
		this.desc2 = desc + "_2";
		this.desc3 = desc + "_3";
		this.desc4 = desc + "_4";
		this.desc5 = desc + "_5";
		this.desc6 = desc + "_6";
		this.desc7 = desc + "_7";
		this.desc8 = desc + "_8";
		this.desc9 = desc + "_9";
		this.desc10 = desc + "_10";
		int intId = (int)id;
		int1 = intId + 1;
		int2 = intId + 2;
		int3 = intId + 3;
		int4 = intId + 4;
		int5 = intId + 5;
		int6 = intId + 6;
		int7 = intId + 7;
		int8 = intId + 8;
		int9 = intId + 9;
		int10 = intId + 10;
		long1 = id + 1;
		long2 = id + 2;
		long3 = id + 3;
		long4 = id + 4;
		long5 = id + 5;
		long6 = id + 6;
		long7 = id + 7;
		long8 = id + 8;
		long9 = id + 9;
		long10 = id + 10;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public String getDesc4() {
		return desc4;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}

	public String getDesc5() {
		return desc5;
	}

	public void setDesc5(String desc5) {
		this.desc5 = desc5;
	}

	public String getDesc6() {
		return desc6;
	}

	public void setDesc6(String desc6) {
		this.desc6 = desc6;
	}

	public String getDesc7() {
		return desc7;
	}

	public void setDesc7(String desc7) {
		this.desc7 = desc7;
	}

	public String getDesc8() {
		return desc8;
	}

	public void setDesc8(String desc8) {
		this.desc8 = desc8;
	}

	public String getDesc9() {
		return desc9;
	}

	public void setDesc9(String desc9) {
		this.desc9 = desc9;
	}

	public String getDesc10() {
		return desc10;
	}

	public void setDesc10(String desc10) {
		this.desc10 = desc10;
	}

	public int getInt1() {
		return int1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public int getInt2() {
		return int2;
	}

	public void setInt2(int int2) {
		this.int2 = int2;
	}

	public int getInt3() {
		return int3;
	}

	public void setInt3(int int3) {
		this.int3 = int3;
	}

	public int getInt4() {
		return int4;
	}

	public void setInt4(int int4) {
		this.int4 = int4;
	}

	public int getInt5() {
		return int5;
	}

	public void setInt5(int int5) {
		this.int5 = int5;
	}

	public int getInt6() {
		return int6;
	}

	public void setInt6(int int6) {
		this.int6 = int6;
	}

	public int getInt7() {
		return int7;
	}

	public void setInt7(int int7) {
		this.int7 = int7;
	}

	public int getInt8() {
		return int8;
	}

	public void setInt8(int int8) {
		this.int8 = int8;
	}

	public int getInt9() {
		return int9;
	}

	public void setInt9(int int9) {
		this.int9 = int9;
	}

	public int getInt10() {
		return int10;
	}

	public void setInt10(int int10) {
		this.int10 = int10;
	}

	public long getLong1() {
		return long1;
	}

	public void setLong1(long long1) {
		this.long1 = long1;
	}

	public long getLong2() {
		return long2;
	}

	public void setLong2(long long2) {
		this.long2 = long2;
	}

	public long getLong3() {
		return long3;
	}

	public void setLong3(long long3) {
		this.long3 = long3;
	}

	public long getLong4() {
		return long4;
	}

	public void setLong4(long long4) {
		this.long4 = long4;
	}

	public long getLong5() {
		return long5;
	}

	public void setLong5(long long5) {
		this.long5 = long5;
	}

	public long getLong6() {
		return long6;
	}

	public void setLong6(long long6) {
		this.long6 = long6;
	}

	public long getLong7() {
		return long7;
	}

	public void setLong7(long long7) {
		this.long7 = long7;
	}

	public long getLong8() {
		return long8;
	}

	public void setLong8(long long8) {
		this.long8 = long8;
	}

	public long getLong9() {
		return long9;
	}

	public void setLong9(long long9) {
		this.long9 = long9;
	}

	public long getLong10() {
		return long10;
	}

	public void setLong10(long long10) {
		this.long10 = long10;
	}

	@Override public String toString() {
		return format("BigItem{id=%d, name='%s'}", id, name);
	}
}
