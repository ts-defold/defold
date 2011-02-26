#include <gtest/gtest.h>

#include <stdint.h>

#include <dlib/hash.h>
#include <dlib/log.h>

#include "../gameobject.h"
#include "../gameobject_private.h"

using namespace Vectormath::Aos;

class CollectionTest : public ::testing::Test
{
protected:
    virtual void SetUp()
    {
        dmGameObject::Initialize();

        m_UpdateContext.m_DT = 1.0f / 60.0f;

        dmResource::NewFactoryParams params;
        params.m_MaxResources = 16;
        params.m_Flags = RESOURCE_FACTORY_FLAGS_EMPTY;
        m_Factory = dmResource::NewFactory(&params, "build/default/src/gameobject/test/collection");
        m_Register = dmGameObject::NewRegister(0, 0);
        dmGameObject::RegisterResourceTypes(m_Factory, m_Register);
        dmGameObject::RegisterComponentTypes(m_Factory, m_Register);
        m_Collection = dmGameObject::NewCollection(m_Factory, m_Register, 1024);
    }

    virtual void TearDown()
    {
        dmGameObject::DeleteCollection(m_Collection);
        dmResource::DeleteFactory(m_Factory);
        dmGameObject::DeleteRegister(m_Register);
        dmGameObject::Finalize();
    }

public:
    dmGameObject::UpdateContext m_UpdateContext;
    dmGameObject::HRegister m_Register;
    dmGameObject::HCollection m_Collection;
    dmResource::HFactory m_Factory;
};

TEST_F(CollectionTest, Collection)
{
    for (int i = 0; i < 10; ++i)
    {
        // NOTE: Coll is local and not m_Collection in CollectionTest
        dmGameObject::HCollection coll;
        dmResource::FactoryResult r = dmResource::Get(m_Factory, "test.collectionc", (void**) &coll);
        ASSERT_EQ(dmResource::FACTORY_RESULT_OK, r);
        ASSERT_NE((void*) 0, coll);

        dmhash_t go01ident = dmHashString64("go1");
        dmGameObject::HInstance go01 = dmGameObject::GetInstanceFromIdentifier(coll, go01ident);
        ASSERT_NE((void*) 0, go01);

        dmhash_t go02ident = dmHashString64("go2");
        dmGameObject::HInstance go02 = dmGameObject::GetInstanceFromIdentifier(coll, go02ident);
        ASSERT_NE((void*) 0, go02);

        dmGameObject::Init(coll);
        dmGameObject::Update(&coll, 0, 1);

        ASSERT_NE(go01, go02);

        dmResource::Release(m_Factory, (void*) coll);
    }
}

TEST_F(CollectionTest, PostCollection)
{
    for (int i = 0; i < 10; ++i)
    {
        dmResource::FactoryResult r;
        dmGameObject::HCollection coll1;
        r = dmResource::Get(m_Factory, "post1.collectionc", (void**) &coll1);
        ASSERT_EQ(dmResource::FACTORY_RESULT_OK, r);
        ASSERT_NE((void*) 0, coll1);

        dmGameObject::HCollection coll2;
        r = dmResource::Get(m_Factory, "post2.collectionc", (void**) &coll2);
        ASSERT_EQ(dmResource::FACTORY_RESULT_OK, r);
        ASSERT_NE((void*) 0, coll2);

        bool ret;
        dmGameObject::Init(coll1);
        dmGameObject::Init(coll2);

        dmGameObject::HCollection collections[2] = {coll1, coll2};
        ret = dmGameObject::Update(collections, 0, 2);
        ASSERT_TRUE(ret);

        dmResource::Release(m_Factory, (void*) coll1);
        dmResource::Release(m_Factory, (void*) coll2);
    }
}

TEST_F(CollectionTest, CollectionFail)
{
    dmLogSetlevel(DM_LOG_SEVERITY_FATAL);
    for (int i = 0; i < 10; ++i)
    {
        // NOTE: Coll is local and not collection in CollectionTest
        dmGameObject::HCollection coll;
        dmResource::FactoryResult r = dmResource::Get(m_Factory, "failing_sub.collectionc", (void**) &coll);
        ASSERT_NE(dmResource::FACTORY_RESULT_OK, r);
    }
    dmLogSetlevel(DM_LOG_SEVERITY_WARNING);
}

TEST_F(CollectionTest, CollectionInCollection)
{
    for (int i = 0; i < 10; ++i)
    {
        // NOTE: Coll is local and not collection in CollectionTest
        dmGameObject::HCollection coll;
        dmResource::FactoryResult r = dmResource::Get(m_Factory, "root1.collectionc", (void**) &coll);
        ASSERT_EQ(dmResource::FACTORY_RESULT_OK, r);
        ASSERT_NE((void*) 0, coll);

        dmhash_t go01ident = dmHashString64("go1");
        dmGameObject::HInstance go01 = dmGameObject::GetInstanceFromIdentifier(coll, go01ident);
        ASSERT_NE((void*) 0, go01);
        ASSERT_NEAR(dmGameObject::GetPosition(go01).getX(), 123.0f, 0.0000f);

        dmhash_t go02ident = dmHashString64("go2");
        dmGameObject::HInstance go02 = dmGameObject::GetInstanceFromIdentifier(coll, go02ident);
        ASSERT_NE((void*) 0, go02);
        ASSERT_NEAR(dmGameObject::GetPosition(go02).getX(), 456.0f, 0.0000f);

        ASSERT_NE(go01, go02);

        ASSERT_TRUE(dmGameObject::Update(&coll, 0x0, 1));

#define ASSERT_P3_NEAR(expected, actual)\
    ASSERT_NEAR(expected.getX(), actual.getX(), 0.001f);\
    ASSERT_NEAR(expected.getY(), actual.getY(), 0.001f);\
    ASSERT_NEAR(expected.getZ(), actual.getZ(), 0.001f);

        Point3 sub1_pos(0.0f, 1000.0f, 0.0f);
        Quat sub1_rot(0.0f, 0.0f, 0.0f, 1.0f);
        Point3 sub2_pos(0.0f, 1000.0f, 0.0f);
        Quat sub2_rot(0.0f, 0.0f, -0.7071f, 0.7071f);
        Point3 parent_pos(0.0f, 20.0f, 0.0f);
        Quat parent_rot(0.0f, 0.7071f, 0.0f, 0.7071f);
        Point3 child_pos(0.0f, 10.0f, 0.0f);
        Quat child_rot(0.0f, 0.0f, 0.0f, 1.0f);

        dmhash_t parent_sub1_ident = dmHashString64("sub1.parent");
        dmGameObject::HInstance parent_sub1 = dmGameObject::GetInstanceFromIdentifier(coll, parent_sub1_ident);
        ASSERT_NE((void*) 0, parent_sub1);
        ASSERT_P3_NEAR((sub1_pos + rotate(sub1_rot, Vector3(parent_pos))), dmGameObject::GetPosition(parent_sub1));
        ASSERT_P3_NEAR((sub1_pos + rotate(sub1_rot, Vector3(parent_pos))), dmGameObject::GetWorldPosition(parent_sub1));

        dmhash_t child_sub1_ident = dmHashString64("sub1.child");
        dmGameObject::HInstance child_sub1 = dmGameObject::GetInstanceFromIdentifier(coll, child_sub1_ident);
        ASSERT_NE((void*) 0, child_sub1);
        ASSERT_P3_NEAR(child_pos, dmGameObject::GetPosition(child_sub1));
        ASSERT_P3_NEAR((sub1_pos + rotate(sub1_rot, Vector3(parent_pos + rotate(parent_rot, Vector3(child_pos))))), dmGameObject::GetWorldPosition(child_sub1));

        dmhash_t parent_sub2_ident = dmHashString64("sub2.parent");
        dmGameObject::HInstance parent_sub2 = dmGameObject::GetInstanceFromIdentifier(coll, parent_sub2_ident);
        ASSERT_NE((void*) 0, parent_sub2);
        ASSERT_P3_NEAR((sub2_pos + rotate(sub2_rot, Vector3(parent_pos))), dmGameObject::GetPosition(parent_sub2));
        ASSERT_P3_NEAR((sub2_pos + rotate(sub2_rot, Vector3(parent_pos))), dmGameObject::GetWorldPosition(parent_sub2));

        dmhash_t child_sub2_ident = dmHashString64("sub2.child");
        dmGameObject::HInstance child_sub2 = dmGameObject::GetInstanceFromIdentifier(coll, child_sub2_ident);
        ASSERT_NE((void*) 0, child_sub2);
        ASSERT_P3_NEAR(child_pos, dmGameObject::GetPosition(child_sub2));
        ASSERT_P3_NEAR((sub2_pos + rotate(sub2_rot, Vector3(parent_pos + rotate(parent_rot, Vector3(child_pos))))), dmGameObject::GetWorldPosition(child_sub2));

#undef ASSERT_P3_NEAR

        // Relative identifiers
        ASSERT_EQ(dmHashString64("a"), dmGameObject::GetAbsoluteIdentifier(go01, "a"));
        ASSERT_EQ(dmHashString64("a"), dmGameObject::GetAbsoluteIdentifier(go02, "a"));
        ASSERT_EQ(dmHashString64("sub1.a"), dmGameObject::GetAbsoluteIdentifier(parent_sub1, "a"));
        ASSERT_EQ(dmHashString64("sub2.a"), dmGameObject::GetAbsoluteIdentifier(parent_sub2, "a"));

        bool ret = dmGameObject::Update(&coll, 0, 1);
        ASSERT_TRUE(ret);

        dmResource::Release(m_Factory, (void*) coll);
    }
}

TEST_F(CollectionTest, CollectionInCollectionChildFail)
{
    dmLogSetlevel(DM_LOG_SEVERITY_FATAL);
    for (int i = 0; i < 10; ++i)
    {
        // NOTE: Coll is local and not collection in CollectionTest
        dmGameObject::HCollection coll;
        dmResource::FactoryResult r = dmResource::Get(m_Factory, "root2.collectionc", (void**) &coll);
        ASSERT_NE(dmResource::FACTORY_RESULT_OK, r);
    }
    dmLogSetlevel(DM_LOG_SEVERITY_WARNING);
}

TEST_F(CollectionTest, DefaultValues)
{
    dmGameObject::HCollection coll;
    dmResource::FactoryResult r = dmResource::Get(m_Factory, "defaults.collectionc", (void**) &coll);
    ASSERT_EQ(dmResource::FACTORY_RESULT_OK, r);
    ASSERT_EQ(2U, coll->m_LevelInstanceCount[0]);
    for (uint32_t i = 0; i < coll->m_LevelInstanceCount[0]; ++i)
    {
        dmGameObject::HInstance instance = coll->m_Instances[coll->m_LevelIndices[i]];
        ASSERT_NE((void*)0, instance);
        Vectormath::Aos::Point3 p = dmGameObject::GetPosition(instance);
        ASSERT_EQ(0.0f, p.getX());
        ASSERT_EQ(0.0f, p.getY());
        ASSERT_EQ(0.0f, p.getZ());
        Vectormath::Aos::Quat r = dmGameObject::GetRotation(instance);
        ASSERT_EQ(0.0f, r.getX());
        ASSERT_EQ(0.0f, r.getY());
        ASSERT_EQ(0.0f, r.getZ());
        ASSERT_EQ(1.0f, r.getW());
    }
    dmResource::Release(m_Factory, (void*) coll);
}

int main(int argc, char **argv)
{
    testing::InitGoogleTest(&argc, argv);

    int ret = RUN_ALL_TESTS();
    return ret;
}
