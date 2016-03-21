import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by aileci on 2015/5/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:applicationContext.xml")
public class Test {
    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setup(){
        this.mockMvc=webAppContextSetup(this.wac).build();
    }
    @org.junit.Test
    @Rollback(false)
    public void index()throws Exception{
        mockMvc.perform((post("/speech/getspeech").param("text","hello controller").param("volume","60").param("rate","1").param("fileType","wav"))).andExpect(status().isOk()).andDo(print());
    }
}
