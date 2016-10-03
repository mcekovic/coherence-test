package org.strangeforest.test.coherence;

import java.io.*;

import com.tangosol.io.pof.*;

public class BigItemPofSerializer implements PofSerializer {

	private static final int VERSION = 0;

	private static final int ID = 0;
	private static final int NAME = 1;
	private static final int DESC1 = 11;
	private static final int DESC2 = 12;
	private static final int DESC3 = 13;
	private static final int DESC4 = 14;
	private static final int DESC5 = 15;
	private static final int DESC6 = 16;
	private static final int DESC7 = 17;
	private static final int DESC8 = 18;
	private static final int DESC9 = 19;
	private static final int DESC10 = 20;
	private static final int INT1 = 21;
	private static final int INT2 = 22;
	private static final int INT3 = 23;
	private static final int INT4 = 24;
	private static final int INT5 = 25;
	private static final int INT6 = 26;
	private static final int INT7 = 27;
	private static final int INT8 = 28;
	private static final int INT9 = 29;
	private static final int INT10 = 30;
	private static final int LONG1 = 31;
	private static final int LONG2 = 32;
	private static final int LONG3 = 33;
	private static final int LONG4 = 34;
	private static final int LONG5 = 35;
	private static final int LONG6 = 36;
	private static final int LONG7 = 37;
	private static final int LONG8 = 38;
	private static final int LONG9 = 39;
	private static final int LONG10 = 40;

	@Override public void serialize(PofWriter out, Object obj) throws IOException {
		BigItem item = (BigItem)obj;
		out.setVersionId(VERSION);
		out.writeLong(ID, item.getId());
		out.writeString(NAME, item.getName());
		out.writeString(DESC1, item.getDesc1());
		out.writeString(DESC2, item.getDesc2());
		out.writeString(DESC3, item.getDesc3());
		out.writeString(DESC4, item.getDesc4());
		out.writeString(DESC5, item.getDesc5());
		out.writeString(DESC6, item.getDesc6());
		out.writeString(DESC7, item.getDesc7());
		out.writeString(DESC8, item.getDesc8());
		out.writeString(DESC9, item.getDesc9());
		out.writeString(DESC10, item.getDesc10());
		out.writeInt(INT1, item.getInt1());
		out.writeInt(INT2, item.getInt2());
		out.writeInt(INT3, item.getInt3());
		out.writeInt(INT4, item.getInt4());
		out.writeInt(INT5, item.getInt5());
		out.writeInt(INT6, item.getInt6());
		out.writeInt(INT7, item.getInt7());
		out.writeInt(INT8, item.getInt8());
		out.writeInt(INT9, item.getInt9());
		out.writeInt(INT10, item.getInt10());
		out.writeLong(LONG1, item.getLong1());
		out.writeLong(LONG2, item.getLong2());
		out.writeLong(LONG3, item.getLong3());
		out.writeLong(LONG4, item.getLong4());
		out.writeLong(LONG5, item.getLong5());
		out.writeLong(LONG6, item.getLong6());
		out.writeLong(LONG7, item.getLong7());
		out.writeLong(LONG8, item.getLong8());
		out.writeLong(LONG9, item.getLong9());
		out.writeLong(LONG10, item.getLong10());
		out.writeRemainder(null);
	}

	@Override public BigItem deserialize(PofReader in) throws IOException {
		BigItem item = new BigItem();
		item.setId(in.readLong(ID));
		item.setName(in.readString(NAME));
		item.setDesc1(in.readString(DESC1));
		item.setDesc2(in.readString(DESC2));
		item.setDesc3(in.readString(DESC3));
		item.setDesc4(in.readString(DESC4));
		item.setDesc5(in.readString(DESC5));
		item.setDesc6(in.readString(DESC6));
		item.setDesc7(in.readString(DESC7));
		item.setDesc8(in.readString(DESC8));
		item.setDesc9(in.readString(DESC9));
		item.setDesc10(in.readString(DESC10));
		item.setInt1(in.readInt(INT1));
		item.setInt2(in.readInt(INT2));
		item.setInt3(in.readInt(INT3));
		item.setInt4(in.readInt(INT4));
		item.setInt5(in.readInt(INT5));
		item.setInt6(in.readInt(INT6));
		item.setInt7(in.readInt(INT7));
		item.setInt8(in.readInt(INT8));
		item.setInt9(in.readInt(INT9));
		item.setInt10(in.readInt(INT10));
		item.setLong1(in.readLong(LONG1));
		item.setLong2(in.readLong(LONG2));
		item.setLong3(in.readLong(LONG3));
		item.setLong4(in.readLong(LONG4));
		item.setLong5(in.readLong(LONG5));
		item.setLong6(in.readLong(LONG6));
		item.setLong7(in.readLong(LONG7));
		item.setLong8(in.readLong(LONG8));
		item.setLong9(in.readLong(LONG9));
		item.setLong10(in.readLong(LONG10));
		in.readRemainder();
		return item;
	}
}