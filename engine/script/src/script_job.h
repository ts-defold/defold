#ifndef DM_SCRIPT_JOB_H
#define DM_SCRIPT_JOB_H

extern "C"
{
#include <lua/lua.h>
}

namespace dmScript
{
    void InitializeJob(lua_State *L, dmConfigFile::HConfig config_file);
    void FinalizeJob(lua_State *L);
}

#endif // DM_SCRIPT_JOB_H
