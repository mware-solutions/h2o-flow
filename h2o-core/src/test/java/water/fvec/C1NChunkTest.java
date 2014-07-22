package water.fvec;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import water.TestUtil;

import java.util.Arrays;

public class C1NChunkTest extends TestUtil {
  @Test
  public void test_inflate_impl() {
    NewChunk nc = new NewChunk(null, 0);

    int[] vals = new int[]{0,1,3,254};
    for (int v : vals) nc.addNum(v,0);

    Chunk cc = nc.compress();
    AssertJUnit.assertEquals(vals.length, cc.len());
    AssertJUnit.assertTrue(cc instanceof C1NChunk);
    for (int i=0;i<vals.length;++i) AssertJUnit.assertEquals(vals[i], cc.at80(i));
    for (int i=0;i<vals.length;++i) AssertJUnit.assertEquals(vals[i], cc.at8(i));

    nc = cc.inflate_impl(new NewChunk(null, 0));
    AssertJUnit.assertEquals(vals.length, nc.len());
    AssertJUnit.assertEquals(vals.length, nc.sparseLen());
    for (int i=0;i<vals.length;++i) AssertJUnit.assertEquals(vals[i], nc.at80(i));
    for (int i=0;i<vals.length;++i) AssertJUnit.assertEquals(vals[i], nc.at8(i));

    Chunk cc2 = nc.compress();
    AssertJUnit.assertEquals(vals.length, cc.len());
    AssertJUnit.assertTrue(cc2 instanceof C1NChunk);
    for (int i=0;i<vals.length;++i) AssertJUnit.assertEquals(vals[i], cc2.at80(i));
    for (int i=0;i<vals.length;++i) AssertJUnit.assertEquals(vals[i], cc2.at8(i));

    AssertJUnit.assertTrue(Arrays.equals(cc._mem, cc2._mem));
  }
}