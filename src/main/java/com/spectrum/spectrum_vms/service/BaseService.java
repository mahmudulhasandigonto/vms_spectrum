
package com.spectrum.spectrum_vms.service;

import java.util.List;

public interface BaseService<Entity, ID> {
   Entity save(Entity entity);

   Entity update(Entity entity) throws Exception;

   void deleteByIds(ID[] ids);

   Entity getDataById(ID id);

   List<Entity> getData();
}