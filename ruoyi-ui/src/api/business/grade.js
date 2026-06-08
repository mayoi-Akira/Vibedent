import request from '@/utils/request'

// 查询年级列表
export function listGrade(query) {
  return request({
    url: '/business/grade/list',
    method: 'get',
    params: query
  })
}

// 查询所有年级（下拉选项用）
export function allGrade() {
  return request({
    url: '/business/grade/all',
    method: 'get'
  })
}

// 查询年级详细
export function getGrade(id) {
  return request({
    url: '/business/grade/' + id,
    method: 'get'
  })
}

// 新增年级
export function addGrade(data) {
  return request({
    url: '/business/grade',
    method: 'post',
    data: data
  })
}

// 修改年级
export function updateGrade(data) {
  return request({
    url: '/business/grade',
    method: 'put',
    data: data
  })
}

// 删除年级
export function delGrade(id) {
  return request({
    url: '/business/grade/' + id,
    method: 'delete'
  })
}
