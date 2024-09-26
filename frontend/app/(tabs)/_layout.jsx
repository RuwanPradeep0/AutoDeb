import { View, Text, Image } from 'react-native'
import { Tabs , Redirect } from 'expo-router'
import React from 'react'
import { icons } from '../../constants'

const TabIcon = ({icon , color , name , focused}) =>{
    return(
        <View className ='items-center justify-center gap-0.5'>
            <Image
                source={icon}
                resizeMode= 'contain'
                tintColor={color}
                className = 'w-6 h-6'
            
            />

            <Text className = {`${focused ? 'font-psemibold' : 'font-pregular'} text-xs `} style={{color:color}}>
                {name}
            </Text>
        </View>

    )
}

const TabsLayout = () => {
  return (
    <>
    <Tabs  
    screenOptions={{
      tabBarShowLabel:false,
      tabBarActiveTintColor:'#fcd34d',
      tabBarInactiveTintColor : '#CDCDE0',
      tabBarStyle: { 
          height: 65,
          backgroundColor : '#0f172a',
          borderTopWidth:1,
          borderTopColor:'#232533'
       }
    }}>
    <Tabs.Screen
        name='chat'
        options={{
          title:'Chat',
          headerShown: false,
          tabBarIcon:({color , focused})=>(
            <TabIcon
              icon={icons.chat}
              color={color}
              name="Chat"
              focused={focused}
              />

          )
        }}/>


<Tabs.Screen
        name='reports'
        options={{
          title:'Reports',
          headerShown: false,
          tabBarIcon:({color , focused})=>(
            <TabIcon
              icon={icons.report}
              color={color}
              name="Reports"
              focused={focused}
              />

          )
        }}/>


<Tabs.Screen
        name='addReports'
        options={{
          title:'Add',
          headerShown: false,
          tabBarIcon:({color , focused})=>(
            <TabIcon
              icon={icons.plus}
              color={color}
              name="Add"
              focused={focused}
              />

          )
        }}/>


<Tabs.Screen
        name='reminders'
        options={{
          title:'Reminders',
          headerShown: false,
          tabBarIcon:({color , focused})=>(
            <TabIcon
              icon={icons.reminders}
              color={color}
              name="Reminders"
              focused={focused}
              />

          )
        }}/>

<Tabs.Screen
        name='profile'
        options={{
          title:'Profile',
          headerShown: false,
          tabBarIcon:({color , focused})=>(
            <TabIcon
              icon={icons.profile}
              color={color}
              name="Profile"
              focused={focused}
              />

          )
        }}/>




    </Tabs>
    
    </>
  )
}

export default TabsLayout