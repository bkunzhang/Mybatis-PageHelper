package mytest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.mapper.CountryMapper;
import com.github.pagehelper.model.Country;
import com.github.pagehelper.util.MybatisHelper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;
/**
 * Created by bkunzhang on 2019/9/2.
 */
public class PageHelperTest {
    @Test
    public void test1() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        List<Country> list = countryMapper.selectAll();
        assertEquals(183, list.size());
        assertEquals(10, list.get(9).getId());

        list = countryMapper.selectAll(new RowBounds(0, 10));
        assertEquals(10, list.size());
        assertEquals(10, list.get(9).getId());

    }

    @Test
    public void test2() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        PageHelper.startPage(1, 15);
        List<Country> list = countryMapper.selectAll();
        assertEquals(15, list.size());
        Page<Country> page = (Page<Country>) list;
        assertEquals(183, page.getTotal());
        assertEquals(15, page.get(14).getId());
    }
}
