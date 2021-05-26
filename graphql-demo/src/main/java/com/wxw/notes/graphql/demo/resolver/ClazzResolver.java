package com.wxw.notes.graphql.demo.resolver;

import com.wxw.notes.graphql.demo.entity.Clazz;
import com.wxw.notes.graphql.demo.response.Result;
import com.wxw.notes.graphql.demo.service.ClazzService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wuxiongwei
 * @date 2021/5/25 16:37
 * @Description
 */
@Component
public class ClazzResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final ClazzService clazzService;

    public ClazzResolver(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    public List<Clazz> listClazz(){
        return clazzService.list();
    }

    public Clazz listClazzByName(String name){
        return clazzService.listByName(name);
    }

    public Result add(String code, String name, String description){
        return clazzService.add(code,name,description);
    }

    public Result del(String id){
        return clazzService.del(id);
    }

    public Result edit(String id,String code, String name, String description){
        return clazzService.edit(id,code,name,description);
    }

    public Result addByInput(Clazz cla){
        return clazzService.addByInput(cla);
    }

}
