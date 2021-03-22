--
-- Created by IntelliJ IDEA.
-- User: zhoutao
-- Date: 2021/3/18
-- Time: 12:32
-- To change this template use File | Settings | File Templates.
--


-- 分布式锁之锁获取
function distributed_lock(key, value)
    -- 说明还没有锁
    if redis.call('setnx', key, value) == true then
        redis.call('pexipire', key, 3000)
        return 1
    else
        -- 如果加锁失败，返回剩余所时间
        return redis.call('pttl', key);
    end
end

-- 分布式锁之锁释放
function distributed_unlock(key, value)
    if redis.call('get', key) == value then
        redis.call('del', key)
    end
end

